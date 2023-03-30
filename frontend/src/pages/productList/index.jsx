import React from 'react'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import SearchCarsList from '../../components/searchCarsList/searchCarsList'


function ProductListScreen() {
  return (
    <div>
     <Header />
     <SearchCarsList />
     <Footer />
    </div>
  )
}

export default ProductListScreen