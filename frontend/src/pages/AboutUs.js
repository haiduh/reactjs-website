import React from "react";
import "../css/AboutUs.css";
import AbtCard from "../components/AbtCard";

function AboutUs() {
  return (
    <div className="about-us">
        <div className="abt-background"></div>
      <div className="text-center wlcm-background">
        <h2 className="abt-hero-headline">Welcome to EDUTRACK</h2>
        <p className="p">
          At EDUTRACK, we're committed to helping students optimise their study
          routines and achieve academic success. Our platform is designed to
          generate personalised roadmaps tailored to each student's unique
          learning style and goals, empowering them to excel in their studies.
        </p>
      </div>
      <div>
        <AbtCard />
      </div>
    </div>
  );
}

export default AboutUs;
