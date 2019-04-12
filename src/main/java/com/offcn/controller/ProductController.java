package com.offcn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.po.Product;
import com.offcn.service.ProductService;
import com.offcn.service.ProductStockService;
import com.offcn.vo.ProductVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Reference(timeout = 6000,retries = 2)
    ProductService productService;

    @Reference(timeout = 6000,retries = 2)
    ProductStockService productStockService;


    @RequestMapping("getlist.do")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public PageResult<ProductVo> getProductList(Integer page, Integer size){
        PageInfo<Product> PageProducts = productService.getAllProduct(page, size);
        //获取分页数据集合
        List<Product> list = PageProducts.getList();
        //创建一个ProductVo集合
        List<ProductVo> productVoList = new ArrayList<>();
        //遍历分页集合
        for(Product product:list){
            ProductVo vo = new ProductVo();
            vo.setId(product.getId()+"");
            vo.setName(product.getName()+"--更新");
            vo.setDescs(product.getDescs());
            vo.setStock(product.getStock());
            vo.setSales(product.getSales());
            vo.setImgurl(product.getImgurl());
            vo.setPrice(product.getPrice());
            productVoList.add(vo);
        }

        return new PageResult<ProductVo>(PageProducts.getTotal(),true,"获取商品列表成功",productVoList);


    }

    @RequestMapping("info.do")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Result getProduct(Long id){
        Product product = productService.findProductById(id);
        ProductVo productVo = new ProductVo();
        productVo.setId(product.getId()+"");
        productVo.setName(product.getName());
        productVo.setDescs(product.getDescs());
        productVo.setPrice(product.getPrice());
        productVo.setImgurl(product.getImgurl());
        productVo.setSales(product.getSales());
        productVo.setStock(product.getStock());
        return new Result(true,"商品信息查询成功",productVo);
    }
}
