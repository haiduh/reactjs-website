import React from "react";
import whatWeDo from '../images/whatwedo.jpg'
import ourMission from '../images/ourmission.jpg'
import whyChooseUs from '../images/whychooseus.jpg'
import "../css/AbtCard.css";

function AbtCard() {
  return (
    <div className="card-container">
      <div className="abt-card what-we-do">
        <h2 className="abt-card-title">What We Do</h2>
        <div className="abt-card-content">
          <p className="abt-card-text">
            Through our innovative roadmap platform, we offer personalised
            guidance and support to students as they navigate the complexities
            of academia and personal growth. Whether it's selecting the right
            courses, preparing for standardised tests, exploring extracurricular
            opportunities, or planning for life after high school, our team of
            experts is here to help every step of the way.
          </p>
          <img
            className="abt-card-image"
            src={whatWeDo}
            alt="profile"
          ></img>
        </div>
      </div>
      <div className="abt-card mission">
        <h2 className="abt-card-title">Our Mission</h2>
        <div className="abt-card-content">
          <p className="abt-card-text">
            Our mission is simple: to empower students to navigate their
            educational journey with confidence and clarity. We believe that
            every student deserves the opportunity to reach their full
            potential, and we're committed to providing the tools and resources
            necessary to make that journey a reality.
          </p>
          <img
            className="abt-card-image"
            src={ourMission}
            alt="profile"
          ></img>
        </div>
      </div>
      <div className="abt-card why-choose-us">
        <h2 className="abt-card-title">Why Choose Us?</h2>
        <div className="abt-card-content">
          <li className="abt-card-text">
            Expert Guidance: Our team consists of experienced educators and
            advisors who are passionate about helping students succeed.
          </li>
          <li className="abt-card-text">
            Personalised Approach: We understand that every student is unique,
            which is why we tailor our roadmap solutions to meet the specific
            needs and goals of each individual.
          </li>
          <li className="abt-card-text">
            Comprehensive Resources: From academic planning tools to career
            exploration resources, we provide everything students need to make
            informed decisions about their future.
          </li>
          <img
            className="abt-card-image"
            src={whyChooseUs}
            alt="profile"
          ></img>
        </div>
      </div>
    </div>
  );
}

export default AbtCard;
