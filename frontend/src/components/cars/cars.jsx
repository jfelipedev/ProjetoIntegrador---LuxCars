
import React, {useState, useEffect} from "react";
import './cars.css';
import { Data } from "./Data";
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";


function Cars() {

  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 768);
    };

    handleResize();

    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  
  return (
    <section className="section">
     <Carousel className="carousel" autoPlay infiniteLoop emulateTouch useKeyboardArrows showStatus={true}
        showIndicators={!isMobile}>
        {Data.map(({ id, image, year, distance, linkMap, title, description, linkdescription }) => {
          return (
            <div className="carsCard" key={id}>              
              <img src={image} alt="Carro" className="carsImg" />          
              <div className="carsInfo">
                <h5 className="carsYear">{year}</h5>
                <h2 className="carsName">{title}</h2>
                <h5 className="carsDistance">{distance}<a href="" className="linkMap">{linkMap}</a></h5>
                <div className="centerCarInfo">
                  <p className="carsDesciption">{description}<a href="" className="linkdescription">{linkdescription}</a></p>
                  <button className="seeMore button">Ver mais</button>
                </div>
              </div>
            </div>
          )
        })}
      </Carousel>
    </section >
  )
}

export default Cars



