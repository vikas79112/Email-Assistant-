
# AI Email Assistant: Enterprise Java Integration 

An intelligent, production-ready system that automates professional email drafting. This project features a **Spring Boot 3** backend containerized via **Docker**, deployed on the cloud, and consumed by a **Chrome Extension** for real-time Gmail automation.

## 📺 Project Demo & Live API
The following video demonstrates the end-to-end flow: from extracting email context in Gmail to receiving a context-aware, AI-powered reply from the live Spring Boot API.

* **[🎥 Watch the Full Extension Demo on Google Drive](https://drive.google.com/file/d/1NwPbd_W8c_gvMnSwTn_vd8ZOc7wB_xGc/view?usp=drivesdk)**
* **[🌐 Live Backend API ](https://email-assistant-backend-kes3.onrender.com/api/email/generate)**

---

## 🏗 System Architecture
The system follows a modern, decoupled client-server architecture designed for high availability:
1.  **Client Layer:** A Chrome Extension (Manifest V3) that uses DOM manipulation to inject buttons into the Gmail UI.
2.  **API Layer:** A RESTful Spring Boot service acting as the central "Brain," handling request validation, CORS management, and AI orchestration.
3.  **AI Engine:** Google Gemini API, integrated via specialized prompt engineering to deliver professional, ready-to-use responses.
4.  **Infrastructure:** Multi-stage Docker builds deployed on the Render cloud platform.

---

## 🛠 Technical Stack
* **Backend:** Java 21, Spring Boot 3, Maven
* **AI Engine:** Google Gemini model: gemini-2.5-flash-lite
* **Extension:** JavaScript, Chrome Manifest V3
* **DevOps:** Docker (Multi-stage), Render Cloud Service
* **Testing:** Postman API Testing

---

## 📡 API Specification & Testing
The backend exposes a highly optimized REST endpoint designed for low-latency JSON communication.

### **Endpoint: Generate Email Reply**
`POST https://email-assistant-backend-kes3.onrender.com/api/email/generate`

**Sample Request Payload:**
```json
{
    "emailContent": "Hi Vikas, we loved your backend project. Are you available for a technical interview?",
    "tone": "professional"
}
````

### **Postman API Verification:**

<img width="1567" height="997" alt="Rest API testing in Postman" src="https://github.com/user-attachments/assets/4618d527-3073-4f46-b386-628e213a6cae" />

*The screenshot above confirms that the Spring Boot service correctly parses payloads, manages the AI handshake, and returns sanitized, single-option replies.*

-----

## 🚀 Key Engineering Features

### **1. Containerization & Modern DevOps**

  * **Multi-Stage Docker Strategy:** Implemented a build-and-run strategy (using Maven 3.9 and Temurin 21) to ensure the production image is lightweight and contains only the necessary JRE.
  * **Environment Security:** Secured sensitive Gemini API keys using Cloud Environment Variables on Render, preventing sensitive data exposure in the source code.

### **2. Backend Design Patterns**

  * **CORS Management:** Configured global Cross-Origin Resource Sharing (CORS) to allow secure, authenticated communication between the browser extension and the cloud-hosted REST API.
  * **Response Sanitization:** Engineered specialized backend prompt wrappers that instruct the LLM to return single-option, concise replies, reducing the need for client-side processing.

### **3. Browser Automation**

  * **Context Injection:** Developed a lightweight content script that interacts with the Gmail DOM to capture email threads dynamically without impacting browser performance.

-----

## 📂 Project Structure

```text
.
├── email-assistant-backend/    # Spring Boot Java Application
│   ├── src/main/java/          # Controller, Service, and Config logic
│   ├── Dockerfile              # Multi-stage Docker configuration
│   └── pom.xml                 # Maven dependencies
├── chrome-extension/           # Manifest V3 Extension
│   ├── contentScript.js        # UI Injection & Fetch logic
│   └── manifest.json           # Extension permissions
└── README.md
```

-----

## 👨‍💻 Developer

**Vikas**

  * **Role:** Software Development Engineer (SDE)
  * **Focus:** Java Backend & Spring Boot Ecosystem
  * **Location:** New Delhi/NCR, India
