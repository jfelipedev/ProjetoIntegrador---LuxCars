import CategoryPanel from './Panels/CategoyPanel';
import CaracteristicPanel from "./Panels/caracteristicPanel"
import CityPanel from './Panels/CityPanel';
import ImagePanel from './Panels/ImagePanel';
import BookingPanel from './Panels/BookingPanel';
import CarPanel from './Panels/CarPanel';
import ContactUSPanel from './Panels/ContactUSPanel';

function Panel(props) {
    switch(props.panel){
        case "Category":
            return (<div className='Panel'>
                <CategoryPanel />
            </div>);
        case "Caracteristic":
            return (<div className='Panel'>
                <CaracteristicPanel />
            </div>);
        case "City":
            return (<div className='Panel'>
                <CityPanel />
            </div>);
        case "Image":
            return (<div className='Panel'>
                <ImagePanel />
            </div>);
        case "Car":
            return (<div className='Panel'>
                <CarPanel />
            </div>);
        case "Booking":
            return (<div className='Panel'>
                <BookingPanel />
            </div>);
        case "ContactUs":
            return (<div className='Panel'>
                <ContactUSPanel />
            </div>);
        default:
            return(<></>);
    }
};

export default Panel;