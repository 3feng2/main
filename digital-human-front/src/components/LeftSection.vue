<template>
  <div class="left-section">
    <div class="logo">DIGITAL HUMAN</div>
    <h1 class="title">Digital Human Visualization System</h1>
    <p class="subtitle">Explore the digital representation of human internal organs</p>
    <Visualization />
  </div>
</template>

<script setup>
import Visualization from './Visualization.vue';
</script>

<style scoped>
/* 左侧固定区域 */
.left-section {
  width: 50%;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.logo {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 2rem;
  background: linear-gradient(45deg, #a9fde3 33%, #56DFCF 66%, #0ABAB5 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  transition: all 0.3s ease;
  cursor: pointer;
}

.logo:hover {
  transform: translateY(-5px);
  filter: brightness(1.2);
}

.title {
  font-size: 2.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  text-align: center;
  opacity: 0;
  transform: translateY(20px);
  animation: fadeInUp 1s ease forwards 0.2s;
}

.subtitle {
  font-size: 1.2rem;
  color: #888;
  margin-bottom: 3rem;
  text-align: center;
  opacity: 0;
  transform: translateY(20px);
  animation: fadeInUp 1s ease forwards 0.4s;
}

/* 动画 */
@keyframes fadeInUp {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 响应式设计 */
@media (max-width: 1024px) {
  .left-section {
    width: 100%;
    height: 50vh;
    position: relative;
  }
}

@media (max-width: 768px) {
  .logo {
    font-size: 2rem;
  }

  .title {
    font-size: 2rem;
  }

  .subtitle {
    font-size: 1rem;
  }
}
</style>