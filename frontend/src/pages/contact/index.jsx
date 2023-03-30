import ContactComp from "../../components/contactComp/index";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import './styles.css'

function Contact() {
  return (
    <div className="container">
      <Header />
      <ContactComp />
      <Footer />
    </div>
  );
}

export default Contact;
