import React, { useState } from "react";
import FAQ from "../components/FAQ";
import Title from "../components/Title";
import ContactUs from "../components/ContactUs";
import HelpContent from "../components/HelpContent";
import "../css/Help.css";

const Help = () => {
  const [faqs, setFAQs] = useState([ //these are the FAQ's.
    {
      question: "How does the roadmap generation process work?",
      answer: "Our roadmap generation process begins with an assessment of your current knowledge level, learning style, and academic goals. Based on this information, our algorithm generates a personalized roadmap tailored to your needs, suggesting the most relevant revision resources and study materials.",
      open: false,
    },
    {
      question: "Where do you get the topics and revision resources from?",
      answer: "Our team of trained tutors specifically devise a list of revision resources best suited for students of all abilities.",
      open: false,
    },
    {
      question: "Can I customize my roadmap based on my specific learning goals and preferences?",
      answer: "Yes, you can customize your roadmap by providing feedback and indicating any specific areas of focus or preferences you have. Our system is designed to adapt to your needs and preferences, ensuring that you receive a personalized learning experience.",
      open: false,
    },
    {
      question: "How can I provide feedback or suggestions for improvement?",
      answer: "We welcome feedback and suggestions for improvement from our users. You can provide feedback through our platform's feedback system, email, or by contacting our support team directly. Your input helps us continuously enhance our services.",
      open: false,
    },
    {
      question: "Can I access the revision resources offline?",
      answer: "No, not as of yet, the majority of our materials are accessible online for convenience and ease of use. However, you may be able to download certain resources for offline access depending on the format and licensing restrictions.",
      open: false,
    },
  ]);

  const toggleFAQ = index => {
    setFAQs(faqs.map((faq, i) => {
      if (i === index) {
        return {...faq, open: !faq.open};   //toggles the question that was clicked
      } else {
        return { ...faq, open: false };     //automatically closes the FAQ Question that is open.
      }
    }))
  }

  return ( 
    <div>
      <div className="help-background"></div> 
      <HelpContent />
      <ContactUs />
      <div className="help-container faqs">
          <Title />   
        {faqs.map((faq, i) => (
          <FAQ faq={faq} index={i} toggleFAQ={toggleFAQ}/>
          ))}
      </div>
    </div>
  );
};

export default Help;
