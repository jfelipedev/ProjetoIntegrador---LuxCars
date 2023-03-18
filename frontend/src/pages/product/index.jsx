import React from 'react'
import Footer from '../../components/footer/footer'
import Header from '../../components/header/header'
import ProductCarousel from '../../components/productCarousel/productCarousel'
import { productData } from '../../components/productCarousel/productData'
import ProductInfo from '../../components/productInfo/productInfo'
import ProductPolicy from '../../components/productPolicy/productPolicy'

function ProductScreen() {
  return (
    <div>
      <Header />
      <ProductCarousel slides={productData} />
      <ProductInfo />
      <ProductPolicy />
      <Footer />
    </div>
  )
}

export default ProductScreen