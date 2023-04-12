import React from 'react'
import Footer from '../../components/footer/footer'
import Header from '../../components/header/header'
import ProductInfo from '../../components/productInfo/productInfo'
import ProductPolicy from '../../components/productPolicy/productPolicy'


function ProductScreen() {
  return (
    <div>
      <Header />
      <ProductInfo />
      <ProductPolicy />
      <Footer />
    </div>
  )
}

export default ProductScreen