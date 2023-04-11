import React, { useState, useEffect } from "react";
import "../../components/faqBoard/styles.css";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import FAQ from "../../components/faqBoard/faqBoard";

function Contact() {

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const [faqs, setfaqs] = useState([
    {
      question: "Socorro! Preciso alterar/cancelar minha reserva!",
      answer:
        "A política de cancelamento está sujeita às regras estabelecidas por cada empresa de aluguel e pode variar dependendo das condições selecionadas no momento da reserva. Antes de cancelar, consulte a política de cancelamento presente na confirmação da sua reserva e, caso tenha mais dúvidas, é melhor entrar em contato com o site no qual a reserva foi efetuada.",
      open: false,
    },
    {
      question: "Qual é a política de reembolso da LuxCars?",
      answer:
        "Como a LuxCars é um buscador, e não um vendedor, não temos uma política de reembolso — isso é tratado unicamente pelo fornecedor que reservou a sua viagem.",
      open: false,
    },
    {
      question: "Os preços incluem taxas e encargos?",
      answer:
        "Todos os preços de voos na LuxCars incluem as taxas e encargos aplicáveis, exceto taxas opcionais como bagagem despachada ou companhias que cobram pela marcação de assentos, transporte de animais de estimação etc. Para esses itens opcionais, você precisa contatar a companhia aérea diretamente. Quer saber que tipo de taxas a sua companhia aérea pode cobrar?",
      open: false,
    },
    {
      question: "Quais são os requisitos de idade para alugar um carro?",
      answer:
        "Os limites de idade variam dependendo de onde e com quem você planeja alugar seu carro. Para obter mais detalhes, sugerimos que você entre em contato diretamente com a locadora de veículos e solicite a política do serviço que pretende contratar. Lembre-se de que a LuxCars não define as políticas relacionadas à idade da pessoa que alugará o veículo.",
      open: false,
    },
  ]);

  const toggleFAQ = (index) => {
    setfaqs(
      faqs.map((faq, i) => {
        if (i === index) {
          faq.open = !faq.open;
        } else {
          faq.open = false;
        }

        return faq;
      })
    );
  };
  return (
    <div>
      <Header />

      <div className="faqs">
        <h1>FAQ (Perguntas frequentes)</h1>
        {faqs.map((faq, i) => (
          <FAQ faq={faq} index={i} toggleFAQ={toggleFAQ} />
        ))}
      </div>
      <Footer />
    </div>
  );
}

export default Contact;
