package com.mdy.sample.controller

import com.mdy.sample.dto.DeleteProductResponse
import com.mdy.sample.dto.GetProductResponse
import com.mdy.sample.dto.PostProductRequest
import com.mdy.sample.dto.PostProductResponse
import com.mdy.sample.dto.PutProductRequest
import com.mdy.sample.dto.PutProductResponse
import com.mdy.sample.service.ProductService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/product")
@RestController
class ProductController(
    private val productService: ProductService,
) {

    @ApiOperation(value = "상품 조회")
    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable id: String,
    ): ResponseEntity<GetProductResponse> {
        val foundProduct = productService.get(id = id.toLong())

        val res = GetProductResponse(
            productId = foundProduct.id.toString(),
            name = foundProduct.name,
            price = foundProduct.price
        )

        return ResponseEntity.ok(res)
    }

    @ApiOperation(value = "상품 등록")
    @PostMapping
    fun postProduct(
        @RequestBody req: PostProductRequest
    ): ResponseEntity<PostProductResponse> {
        val createdProduct = productService.register(
            name = req.name,
            price = req.price,
        )

        val res = PostProductResponse(
            productId = createdProduct.id.toString(),
            name = createdProduct.name,
            price = createdProduct.price
        )

        return ResponseEntity.ok(res)
    }

    @ApiOperation(value = "상품 정보 수정")
    @PutMapping("/{id}")
    fun putProduct(
        @PathVariable id: String,
        @RequestBody req: PutProductRequest,
    ): ResponseEntity<PutProductResponse> {
        val updatedProduct = productService.update(
            id = id.toLong(),
            name = req.name,
            price = req.price,
        )

        val res = PutProductResponse(
            productId = updatedProduct.id.toString(),
            name = updatedProduct.name,
            price = updatedProduct.price,
        )

        return ResponseEntity.ok(res)
    }

    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/{id}")
    fun deleteProduct(
        @PathVariable id: String,
    ): ResponseEntity<DeleteProductResponse> {
        productService.remove(id = id.toLong())

        val res = DeleteProductResponse()
        return ResponseEntity.ok(res)
    }
}