import React, { useState } from "react";
import axios from "axios";

function ContactUs() {
  const [values, setValues] = useState({
    name: "",
    email: "",
    message: "",
  });

  const handleChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    
    if (!values.name || !values.email || !values.message) {
      setErrorMessage("Please fill in all fields.");
      return; 
    }

    axios
      .post("http://localhost:8080/contactus", values)
      .then((res) => {
        setSuccessMessage("Thanks, we will be in touch as soon as possible.");
        setErrorMessage("");
      })
      .catch((err) => {
        setSuccessMessage("");
        setErrorMessage("Submission failed. Please try again.");
      });
  };

  return (
    <div className="h-full flex justify-center flex-col mb-4">
      <div className="w-1/3 m-auto p-8 rounded-lg shadow-lg bg-white">
        <h2 className="text-4xl font-bold flex items-center">
          <span>Contact Us</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="h-12 ml-2"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M12 20.25c4.97 0 9-3.694 9-8.25s-4.03-8.25-9-8.25S3 7.444 3 12c0 2.104.859 4.023 2.273 5.48.432.447.74 1.04.586 1.641a4.483 4.483 0 0 1-.923 1.785A5.969 5.969 0 0 0 6 21c1.282 0 2.47-.402 3.445-1.087.81.22 1.668.337 2.555.337Z"
            />
          </svg>
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="my-2 flex flex-col">
            <label htmlFor="name">Name</label>
            <input
              type="text"
              className="border-2 p-2"
              id="name"
              name="name"
              value={values.name}
              onChange={handleChange}
            />
          </div>
          <div className="my-2 flex flex-col">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              className="border-2 p-2"
              id="email"
              name="email"
              value={values.email}
              onChange={handleChange}
            />
          </div>
          <div className="my-2 flex flex-col">
            <label htmlFor="message">Message</label>
            <textarea
              className="border-2 p-2"
              id="message"
              name="message"
              value={values.message}
              onChange={handleChange}
            />
          </div>
          {errorMessage && (
            <div className="text-white bg-red-600 rounded-md text-center my-2">
              {errorMessage}
            </div>
          )}
          {successMessage && (
            <div className="text-white bg-green-600 rounded-md text-center my-2">
              {successMessage}
            </div>
          )}
          <button
            type="submit"
            className="my-2 mx-auto bg-blue-700 text-white w-32 rounded-lg p-2 hover:bg-blue-900 transition-colors duration-200"
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default ContactUs;
