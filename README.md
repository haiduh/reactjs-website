# ReactJS Website with Spring Boot/Node.js Backend

![Project Banner](./images/project-banner.png) <!-- Replace with actual image path -->

Welcome to the **ReactJS Website** project! This repository contains a fully functional website with a **ReactJS** frontend and a backend powered by **Spring Boot** or **Node.js** (depending on your choice). The project also includes a comprehensive **"Contact Us"** page, featuring an FAQ section and a form that connects to the backend to handle user queries.

## 🌟 Features

- **ReactJS Frontend**: Fast and responsive interface built with React.
- **Backend Options**: 
  - Spring Boot (Java-based backend)
  - Node.js (JavaScript runtime backend)
- **Contact Us Page**:
  - FAQs: Frequently asked questions for user support.
  - Contact Form: Submit inquiries, connected to the backend for seamless communication.
- **About Us Page**: Information about the project and the team behind it.
- **API Testing**: 
  - Fully tested API endpoints using **Postman**.

![Website Demo](./gifs/website-demo.gif) <!-- Replace with actual GIF path -->

## 🚀 Technologies Used

### Frontend
- **ReactJS**: UI library for building dynamic user interfaces.
- **HTML/CSS**: For layout and styling.
- **JavaScript/ES6+**: Powering the frontend logic.
- **VS Code**: Integrated Development Environment (IDE) used for developing the frontend.

### Backend (Choose One)
- **Spring Boot**: Java-based framework for building RESTful APIs.
- **Node.js**: JavaScript runtime for backend logic.
- **PostgreSQL/MySQL**: Database integration for storing contact form submissions.
- **IntelliJ**: Integrated Development Environment (IDE) used for developing the Spring Boot backend.

### API Testing
- **Postman**: Used for testing backend API endpoints to ensure smooth functionality.

## 📂 Project Structure

This is an example of what it looks like:

```plaintext
root/
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── App.js
│   └── package.json
├── backend/
│   ├── src/
│   │   ├── main/
│   │   ├── test/
│   └── pom.xml / package.json
└── README.md
```

## 🔧 Setup & Installation

### Frontend
1. Navigate to the `frontend/` directory.
   
2. Install dependencies:

   ```bash
   npm install
   
3. Run the frontend server:
   
   ```bash
   npm start

### Backend (Spring Boot)
1. Navigate to the `backend/` directory.

2. Build the project:

   ```bash
   mvn clean install

3. Run the backend server:

   ```bash
   mvn spring-boot:run

The backend will be running at `http://localhost:8080`.

## Backend (Node.js Alternative)
1. Navigate to the `backend/` directory.

2. Install dependencies:

   ```bash
   npm install

3. Run the server:

   ```bash
   npm start

The backend will be running at `http://localhost:5000`.

## 📬 Contact Us Page

The **Contact Us** page is designed to allow users to submit inquiries directly to the backend. Here’s what it includes:

- **FAQ Section**: Common questions and answers.
- **Contact Form**: Submits user details and queries to the backend. The data is securely stored in the database and processed accordingly.

## 📖 About Us Page

The **About Us** page provides information about the project and the team behind it. It outlines the vision, mission, and goals of the website, as well as the technologies and methodologies used in its development.

## 🛠 API Testing with Postman

**Postman** has been used extensively to test the website's functionality. 

## 🤝 Contributions

Feel free to contribute! Fork the repo, make a pull request, and let's build something great together.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

