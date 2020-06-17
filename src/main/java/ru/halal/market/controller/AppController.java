package ru.halal.market.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.halal.market.model.Product;
import ru.halal.market.service.impl.ProductServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Controller
public class AppController {
    private final ProductServiceImpl productService;

    @Value("${upload.path}")
    private String uploadPath;

    public AppController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewProducts(
            @RequestParam(required = false) String filter,
            Model model,
            @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Product> page;

        if (filter != null && !filter.isEmpty()) {
            page = productService.findAllByNameContaining(filter, pageable);
        } else {
            page = productService.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        return "products";
    }

    @GetMapping("new_product")
    public String showNewProductForm(
            Model model
    ) {
        final Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("new_product")
    public String saveProduct(
            @ModelAttribute Product product,
            @RequestParam("customFile") MultipartFile file,
            Model model
    ) throws IOException {
        System.out.println(product.getFilename());
        if (product.getMadeDate() == null) {
            product.setMadeDate(new Date());
        }
        if (product.getFilename() == null) {
            product.setFilename("logo.png");
        }
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String resultFilename = file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            product.setFilename(resultFilename);
        }
        model.addAttribute(product);
        product.setSale(true);
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView showEditProductForm(
            @PathVariable(name = "id") Long id
    ) {
        Product product = productService.get(id);
        ModelAndView mav = new ModelAndView("edit_product");
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.delete(id);

        return "redirect:/";
    }
}
