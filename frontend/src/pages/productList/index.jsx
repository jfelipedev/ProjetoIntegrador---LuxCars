import React from 'react'
import Header from '../../components/header/header'
import Footer from '../../components/footer/footer'
import SearchCarsList from '../../components/searchCarsList/searchCarsList'
import { useLocation } from 'react-router-dom'



function ProductListScreen() {

  const { state } = useLocation();
  console.log(state);


  return (
    <div>
      <Header />
      <SearchCarsList
        selectedCity={state.selectedCity}
        selectedCategory={state.selectedCategory}
        startDate={state.startDate}
        endDate={state.endDate} />
      <Footer />
    </div>
  )
}

export default ProductListScreen
