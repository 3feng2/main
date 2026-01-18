
<template>
  <div class="home">
    <!-- 背景装饰 -->
    <div class="background-decoration">
      <div class="grid-overlay"></div>
      <div class="floating-particles">
        <div class="particle" v-for="i in 50" :key="i" :style="getParticleStyle(i)"></div>
      </div>
    </div>

    <div class="auth-wrapper">
      <div class="auth-card">
        <!-- Left image panel -->
        <div class="image-panel">
          <img src="../assets/B1.png" alt="Signup image" />
        </div>

        <!-- Right form panel -->
        <form class="form-panel" @submit.prevent="onSubmit" novalidate>
        <div class="logo"></div>
        <h2>Create an account</h2>

        <button type="button" class="google-btn">
          <img src="https://www.svgrepo.com/show/475656/google-color.svg" />
          Create account with Google
        </button>

        <div class="divider"><span>Or</span></div>

        <div class="field">
          <label>Email Address</label>
          <input v-model.trim="form.email" type="email" placeholder="Enter your email address" />
          <small v-if="errors.email">{{ errors.email }}</small>
        </div>

        <div class="field">
          <label>Full Name</label>
          <input v-model.trim="form.fullName" type="text" placeholder="Enter your full name" />
          <small v-if="errors.fullName">{{ errors.fullName }}</small>
        </div>

        <div class="field password">
          <label>Password</label>
          <input :type="showPassword ? 'text' : 'password'" v-model="form.password" placeholder="Create your password" />
          <span @click="showPassword = !showPassword">👁</span>
          <small v-if="errors.password">{{ errors.password }}</small>
        </div>

        <button class="primary" :disabled="submitting || hasErrors()">
          {{ submitting ? 'Creating...' : 'Create an account' }}
        </button>

        <p class="login">Already have an account? <router-link to="/login">Login</router-link></p>

        <div class="socials">
          <i class="fab fa-facebook"></i>
          <i class="fab fa-twitter"></i>
          <i class="fab fa-instagram"></i>
          <i class="fab fa-linkedin"></i>
        </div>
      </form>
    </div>
  </div>
  </div>
</template>

<script setup>
import { reactive, ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const showPassword = ref(false)
const submitting = ref(false)
const serverError = ref("");

// 生成粒子样式
const getParticleStyle = (_index) => {
  const size = Math.random() * 4 + 2
  const left = Math.random() * 100
  const top = Math.random() * 100
  const delay = Math.random() * 3
  const duration = Math.random() * 3 + 2
  
  return {
    width: `${size}px`,
    height: `${size}px`,
    left: `${left}%`,
    top: `${top}%`,
    animationDelay: `${delay}s`,
    animationDuration: `${duration}s`
  }
}

const form = reactive({
  fullName: '',
  email: '',
  password: ''
})


const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

const errors = computed(() => { const e = { fullName: "", email: "", password: "" };
  if (!form.fullName) e.fullName = "Full name is required.";
  if (!form.email) e.email = "Email is required."; 
  else if (!emailRegex.test(form.email)) e.email = "Invalid email format.";
  if (!form.password) e.password = "Password is required."; 
  else if (form.password.length < 8) e.password = "Minimum 8 characters required."; return e; });

const hasErrors = () => Object.values(errors.value).some(Boolean)


// Submit Handler
async function onSubmit() {
  serverError.value = "";

  if (hasErrors()) return;

  submitting.value = true;

  try {
    // Call the actual backend API
    const res = await fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        username: form.fullName, // Map fullName to username as per backend API
        email: form.email,
        password: form.password,
      }),
    });
    
    const data = await res.json();
    
    // Redirect on success
    if (res.ok) {
      // alert("Account created successfully!"); // Commented out to match the clean aesthetic
      router.push('/login');
    } else {
        throw new Error(data.message || `Signup failed with status: ${res.status}`);
    }

  } catch (err) {
    serverError.value = err.message;
  } finally {
    submitting.value = false;
  }
  
}
</script>

<style scoped>
/* WelcomePage Background Styles */
.home {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: relative;
  background: linear-gradient(135deg, #020006 0%, #183de2 100%);
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(5px);
  -webkit-backdrop-filter: blur(5px);
}

/* 背景装饰 */
.background-decoration {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

/* 网格覆盖层 */
.grid-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: 
    linear-gradient(rgba(255, 255, 255, 0.1) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255, 255, 255, 0.1) 1px, transparent 1px);
  background-size: 50px 50px;
  animation: gridMove 20s linear infinite;
}

@keyframes gridMove {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(50px, 50px);
  }
}

/* 浮动粒子 */
.floating-particles {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.particle {
  position: absolute;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 50%;
  animation: float 2s ease-in-out infinite;
  opacity: 0;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0) scale(1);
    opacity: 0;
  }
  50% {
    transform: translateY(-20px) scale(1.2);
    opacity: 1;
  }
}

/* 1. Glassmorphism Background: IMPORTANT for the blur effect to work */
.auth-wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  /* Changed background to a more vibrant one for the glass effect */
  background: linear-gradient(135deg, #020006 0%, #042de6 100%);
}

/* 2. Glassmorphism Card Effect */
.auth-card {
  display: grid;
  grid-template-columns: 1fr 1fr;
  max-width: 1000px;
  width: 90%; /* Slight adjustment for better mobile/screen fit */
  
  /* Glassmorphism Properties */
  background: rgba(255, 255, 255, 0.2); /* Semi-transparent white */
  border-radius: 24px;
  overflow: hidden;
  box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.3); /* Darker, more prominent shadow */
  backdrop-filter: blur(20px); /* The Frosted Glass Effect */
  -webkit-backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.4); /* Light border */
}

/* 3. Image Panel Adjustment */
.image-panel {
  /* Keep the image but remove the solid gradient, or apply a subtle style */
  background: rgba(201, 16, 16, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-panel img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.8; /* Make image slightly transparent */
}

/* Form Panel Colors: Make text white/light for contrast against the dark background */
.form-panel {
  padding: 3rem;
  color: #fff; /* Light text color for contrast */
}

.logo {
  font-size: 2rem;
  margin-bottom: .5rem;
}

h2 {
  margin-bottom: 1.5rem;
  color: #fff; /* Ensure heading is white */
}

/* Button Adjustments */
.google-btn {
  width: 100%;
  padding: .9rem;
  border: 1px solid rgba(255, 255, 255, 0.4); /* Light border */
  border-radius: 12px;
  background: rgba(255, 255, 255, 0.1); /* Slightly transparent background */
  display: flex;
  align-items: center;
  justify-content: center;
  gap: .6rem;
  cursor: pointer;
  color: #fff; /* Text color */
}

.google-btn img { width: 18px }

.divider {
  text-align: center;
  margin: 1.5rem 0;
  position: relative;
}

.divider::before,
.divider::after {
  content: '';
  position: absolute;
  top: 50%;
  width: 40%;
  height: 1px;
  background: rgba(0, 0, 0, 0.4); /* Light separator */
}

.divider::before { left: 0 }
.divider::after { right: 0 }

.divider span { font-size: .85rem; color: #ccc } /* Lighter span text */

.field { margin-bottom: 1.1rem }

label { font-size: .85rem; color: #fff } /* White label text */

/* Input Field Glass Style */
input {
  width: 100%;
  padding: .85rem 1rem;
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.4); /* Light border */
  margin-top: .3rem;
  background: rgba(255, 255, 255, 0.1); /* Very slight transparency */
  color: #fff; /* White text input */
}

/* Change placeholder color for better visibility */
input::placeholder {
    color: #eee;
    opacity: 0.8;
}


.password { position: relative }
.password span {
  position: absolute;
  right: 14px;
  top: 41px;
  cursor: pointer;
  color: #060606; /* White eye icon */
}

small { color: #f9d8d8 } /* Adjusted error color for dark background */

.primary {
  margin-top: 1.2rem;
  width: 100%;
  padding: 1rem;
  border-radius: 14px;
  border: none;
  /* Keep the primary color strong for the button */
  background: #000000;
  color: #eeeeee;
  font-weight: 600;
}

.login {
  text-align: center;
  margin-top: 1rem;
  color: #ded2d2; /* White text */
}

.login a {
    color: #02063c; /* A vibrant link color */
}

.socials {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 1.2rem;
}

.socials i {
    font-size: 1.5rem;
    color: #fff; /* White icons */
    cursor: pointer;
    transition: color 0.3s;
}

.socials i:hover {
    color: #9333ea;
}

/* Media Query to ensure the layout works on smaller screens */
@media (max-width: 800px) {
  .auth-card {
    grid-template-columns: 1fr; /* Stack the panels */
    width: 95%;
  }

  .image-panel {
    display: none; /* Hide image panel on small screens */
  }

  .form-panel {
    padding: 2rem;
  }
}
</style>