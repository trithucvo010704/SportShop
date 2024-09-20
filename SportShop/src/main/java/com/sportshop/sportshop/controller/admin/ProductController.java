package com.sportshop.sportshop.controller.admin;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.enums.BrandEnum;
import com.sportshop.sportshop.enums.CategoryEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
import com.sportshop.sportshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // View all product
    @GetMapping
    public ModelAndView getAllProducts(){

        ModelAndView mav = new ModelAndView("/admin/product/management");
        mav.addObject("products", productService.getAllProducts());

        return mav;
    }

    // View product by ID
    @GetMapping("/{productId}")
    public ModelAndView getProduct(@PathVariable Long productId){

        ModelAndView mav = new ModelAndView("/admin/product/management");

        mav.addObject("product", productService.getProductById(productId));

        return mav;
    }

    // Create product
    @GetMapping("/create")
    public ModelAndView createProduct(){
        return new ModelAndView("/admin/product/create")
                    .addObject("newProduct",  new ProductRequest())
                    .addObject("categories", CategoryEnum.values())
                    .addObject("brands", BrandEnum.values())
                    .addObject("genders", GenderEnum.values())
                    .addObject("sizes", SizeEnum.values());
    }

    @PostMapping("/create")
    public String createProduct(ProductRequest request, Model model){
        try {
            productService.createProduct(request);
            model.addAttribute("notification", "Success");
            return "/admin/notification";
        } catch (Exception e) {
            model.addAttribute("notification", "Fail");
            model.addAttribute("message", e.getMessage());
            return "/admin/notification";
        }
    }



}
