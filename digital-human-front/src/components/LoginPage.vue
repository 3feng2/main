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
        <div class="image-panel">
          <img src="../assets/B1.png" alt="Login image" />
        </div>

        <form class="form-panel" @submit.prevent="onSubmit" novalidate>
        <div class="logo"></div>
        <h2>Welcome Back!</h2>

        <button type="button" class="google-btn">
          <img src="https://www.svgrepo.com/show/475656/google-color.svg" />
          Sign in with Google
        </button>

        <div class="divider"><span>Or</span></div>

        <div class="field">
          <label>Email Address</label>
          <input
            v-model.trim="form.email"
            type="email"
            placeholder="Enter your email address"
          />
          <small v-if="triedSubmit && !emailValid">Please enter a valid email.</small>
        </div>

        <div class="field password">
          <label>Password</label>
          <input
            :type="showPassword ? 'text' : 'password'"
            v-model="form.password"
            placeholder="Enter your password"
          />
          <span @click="showPassword = !showPassword">👁</span>
          <small v-if="triedSubmit && !passwordValid">Password must be at least 6 characters.</small>
        </div>

        <p class="forgot-password">
          <a href="#">Forgot Password?</a>
        </p>
        
        <button class="primary" type="submit" :disabled="loading">
          {{ loading ? 'Signing In...' : 'Sign in' }}
        </button>

        <small v-if="error" class="error-alert">{{ error }}</small>

        <p class="login">
          Don't have an account? 
          <router-link to="/signup">Create one</router-link>
        </p>

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
// Assumes you have Vue Router or a method to handle navigation ('login' or 'navigate' event)

// eslint-disable-next-line no-undef
const emit = defineEmits(['login', 'navigate'])

const router = useRouter()

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
    email: '',
    password: '',
})

const loading = ref(false)
const error = ref('')
const triedSubmit = ref(false)
const showPassword = ref(false)

// Validation logic
const emailValid = computed(() => /\S+@\S+\.\S+/.test(form.email))
const passwordValid = computed(() => form.password.length >= 6)
const formValid = computed(() => emailValid.value && passwordValid.value)

async function onSubmit() {
    triedSubmit.value = true
    error.value = ''

    if (!formValid.value) return

    loading.value = true
    try {
        // Call the actual backend API
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: form.email,
                password: form.password
            })
        })
        
        const data = await response.json()
        
        if (response.ok) {
            // On success, navigate to app root page
            router.push('/app')
        } else {
            // Invalid credentials
            error.value = data.message || 'Invalid email or password. Please try again.'
        }
        
    } catch (e) {
        error.value = e?.message || 'Unable to sign in. Please try again.'
    } finally {
        loading.value = false
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

/* =================================== */
/* GLASSMORPHISM STYLES (FROM SIGNUP) */
/* =================================== */

/* 1. Glassmorphism Background */
.auth-wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #020006 0%, #042de6 100%);
}

/* 2. Glassmorphism Card Effect & Layout */
.auth-card {
  display: grid;
  grid-template-columns: 1fr 1fr;
  max-width: 1000px;
  width: 90%;
  
  /* Glassmorphism Core Properties */
  background: rgba(255, 255, 255, 0.2); 
  border-radius: 24px;
  overflow: hidden;
  box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.3); 
  backdrop-filter: blur(20px); 
  -webkit-backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.4); 
}

/* 3. Image Panel Adjustment */
.image-panel {
  background: rgba(201, 16, 16, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-panel img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.8; 
}

/* Form Panel Colors and Structure */
.form-panel {
  padding: 3rem;
  color: #fff;
}

.logo {
  font-size: 2rem;
  margin-bottom: .5rem;
}

h2 {
  margin-bottom: 1.5rem;
  color: #fff;
}

/* Google Button Styles */
.google-btn {
  width: 100%;
  padding: .9rem;
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 12px;
  background: rgba(255, 255, 255, 0.1); 
  display: flex;
  align-items: center;
  justify-content: center;
  gap: .6rem;
  cursor: pointer;
  color: #fff;
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
  background: rgba(255, 255, 255, 0.4); /* Used white for better visibility against dark background */
}

.divider::before { left: 0 }
.divider::after { right: 0 }

.divider span { font-size: .85rem; color: #ccc }

.field { margin-bottom: 1.1rem }

label { font-size: .85rem; color: #fff }

/* Input Field Glass Style */
input {
  width: 100%;
  padding: .85rem 1rem;
  border-radius: 12px;
  border: 1px solid rgba(255, 255, 255, 0.4); 
  margin-top: .3rem;
  background: rgba(255, 255, 255, 0.1); 
  color: #fff;
}

input::placeholder {
  color: #eee;
  opacity: 0.8;
}

.password { position: relative }
.password span {
  position: absolute;
  right: 14px;
  /* Adjusted top for better alignment after removing the Bootstrap form structure */
  top: 41px; 
  cursor: pointer;
  color: #fff;
}

small { color: #f9d8d8 } /* Error messages */

.error-alert { /* Style for the main error message below the button */
    display: block;
    margin-top: 1rem;
    padding: 0.5rem;
    border: 1px solid #f9d8d8;
    border-radius: 8px;
    text-align: center;
}

.forgot-password {
    text-align: right;
    margin-bottom: 1rem;
    font-size: 0.85rem;
}

.forgot-password a {
    color: #ded2d2;
    text-decoration: none;
}


.primary {
  margin-top: 1.2rem;
  width: 100%;
  padding: 1rem;
  border-radius: 14px;
  border: none;
  background: #000000;
  color: #eeeeee;
  font-weight: 600;
  cursor: pointer;
}

.primary:disabled {
    opacity: 0.6;
    cursor: not-allowed;
}

.login {
  text-align: center;
  margin-top: 1rem;
  color: #ded2d2; 
}

.login a {
  color:#02063c; 
  text-decoration: underline; 
}

.socials {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 1.2rem;
}

.socials i {
  font-size: 1.5rem;
  color: #fff; 
  cursor: pointer;
  transition: color 0.3s;
}

.socials i:hover {
  color: #042de6;
}

/* Media Query for Responsiveness */
@media (max-width: 800px) {
  .auth-card {
    grid-template-columns: 1fr; 
    width: 95%;
  }

  .image-panel {
    display: none; 
  }

  .form-panel {
    padding: 2rem;
  }
}
</style>