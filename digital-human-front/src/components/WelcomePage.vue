<script setup lang="ts">
import { useRouter } from 'vue-router'
import { Upload, Lock, ArrowRight } from '@element-plus/icons-vue'


const router = useRouter()


const goToPointClouds = () => {
  router.push('/modulemanager')
}

// 生成粒子样式
const getParticleStyle = (_index: number) => {
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
</script>


<template>
  <div class="home">
    <!-- 背景装饰 -->
    <div class="background-decoration">
      <div class="grid-overlay"></div>
      <div class="floating-particles">
        <div class="particle" v-for="i in 50" :key="i" :style="getParticleStyle(i)"></div>
      </div>
    </div>


    <div class="welcome-section fade-in">
      <div class="hero-content">
        <h1 class="hero-title">
          <span class="title-line">欢迎使用</span>
          <span class="title-line accent">3D数字人体可视化系统</span>
        </h1>
        <p class="description">
          本系统提供器官数据的上传、管理和三维可视化展示功能。
        </p>
        
        <div class="cta-section">
          <el-button
            type="primary"
            size="large"
            @click="goToPointClouds"
            class="cta-button"
          >
            <el-icon><upload /></el-icon>
            开始使用系统
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 欢迎页面样式 */
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

/* 欢迎区域 */
.welcome-section {
  position: relative;
  z-index: 10;
  max-width: 800px;
  padding: 40px;
  text-align: center;
}

/* 渐入动画 */
.fade-in {
  animation: fadeIn 1s ease-in forwards;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 英雄标题 */
.hero-title {
  font-size: 3rem;
  font-weight: 700;
  color: #fff;
  margin-bottom: 20px;
  line-height: 1.2;
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 2rem;
  }
}

.title-line {
  display: block;
  margin-bottom: 10px;
}

.title-line.accent {
  background: linear-gradient(45deg, #FFD700, #FFA500);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* 描述文本 */
.description {
  font-size: 1.2rem;
  color: rgba(255, 255, 255, 0.9);
  margin-bottom: 40px;
  line-height: 1.6;
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}

/* CTA区域 */
.cta-section {
  display: flex;
  gap: 20px;
  justify-content: center;
  flex-wrap: wrap;
}

/* 主要按钮 */
.cta-button {
  padding: 15px 30px;
  font-size: 1.1rem;
  font-weight: 600;
  border-radius: 50px;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.cta-button:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.3);
}
</style>