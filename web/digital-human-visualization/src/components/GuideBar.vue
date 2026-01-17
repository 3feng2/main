<script setup>
// 导航栏组件
</script>

<template>
  <nav class="guide-bar">
    <div class="guide-bar-container">
      <!-- 左侧logo或标题 -->
      <div class="guide-bar-logo">
        <h1>数字人体可视化</h1>
      </div>
      
      <!-- 右侧导航链接 -->
      <div class="guide-bar-nav">
        <a href="#" class="nav-link">首页</a>
        <a href="#" class="nav-link">器官列表</a>
        <a href="#" class="nav-link">3D模型</a>
        <a href="#" class="nav-link">关于我们</a>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.guide-bar {
  background-color: #1a1a2e;
  color: white;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
}

.guide-bar-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
}

.guide-bar-logo h1 {
  margin: 0;
  font-size: 20px;
  font-weight: 600;
  color: #fff;
}

.guide-bar-nav {
  display: flex;
  gap: 24px;
}

.nav-link {
  color: #e0e0e0;
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #64ffda;
}
</style>