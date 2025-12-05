Feedback Analyzer – Spring Boot + AI/NLP Project

A complete AI-powered feedback analysis system built using Spring Boot, JWT Authentication, and a simple custom NLP engine.
It automatically analyzes feedback, detects sentiment, extracts keywords, predicts category, and provides analytics.

->Features
      User & Admin Authentication
      WT-based secure login
      Role-based access control

      /admin/** → Admin only  
      /feedback/** → Logged-in users
      /ai/** → Public (used for NLP previews if needed)

-> Feedback Processing
      Automatically generates:
      Sentiment (Positive / Negative / Neutral)
      Keywords
      Category detection
      Sentiment score
      Confidence score

->Analytics
      Total feedback count
      Sentiment distribution
      Top keywords
      Category-wise filter
      Date range filter

-> Tech Stack
      Layer	Technology
      Backend	Spring Boot 3, Spring Security 6
      Auth	JWT Token
      Database	MySQL (Hibernate + JPA)
      AI/NLP	Custom rule-based engine
      Build Tool	Maven
      
-> Project Structure
com.anantha.feedbackanalyzer
 ┣ controller
 ┣ entity
 ┣ repository
 ┣ security
 ┣ service
 ┣ dto
 ┗ exception

->Authentication Endpoints
    Register
    POST /auth/register
    Body:
    
    {
      "name": "Anantha",
      "email": "test@gmail.com",
      "password": "123456"
    }
    
  Login
    POST /auth/login
    
    
    Body:
    
    {
      "email": "test@gmail.com",
      "password": "123456"
    }
    
    
    Response contains JWT token

 ->Feedback Endpoints (Require Token)
    Create Feedback
      POST /feedback/create/{userId}


      Body:
      
      {
        "message": "The product quality is amazing but delivery was slow",
        "rating": 4
      }
      
   Get All Feedback
      GET /feedback
      
   Filter by Category
      GET /feedback/category/{cat}
      
  Filter by Sentiment
      GET /feedback/sentiment/{type}
      
   Search Keyword
      GET /feedback/search/{keyword}

->AI Endpoints (Public)
  GET /ai/sentiment?text=...
  GET /ai/keywords?text=...
  GET /ai/category?text=...

-> Database Schema (Important Tables)
    users
    id
    name
    email
    password
    created_at
    feedback
    id
    message
    rating
    sentiment
    category
    created_at
    user_id
    ai_result
    feedback_id (unique)
    sentiment_score
    keywords
    confidence
    feedback_category
    id
    category_name
    description

-> Admin Features

Admins can:
  View all feedback

View analytics
  Manage categories
  (Admin role inserted manually in DB)


->Run the project
    1. Configure MySQL
    create database feedback_analyzer;
    
    Update application.properties:
    
    spring.datasource.url=jdbc:mysql://localhost:3306/feedback_analyzer
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    
    2. Run
    mvn spring-boot:run

 
 ->Contributing

Pull requests are welcome.
