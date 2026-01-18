<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

// 控制下拉菜单显示和展开状态
const dropdownState = ref({
  show: false,
  expanded: false
});

// 切换下拉菜单
const toggleDropdown = () => {
  dropdownState.value.show = !dropdownState.value.show;
  if (dropdownState.value.show) {
    // 延迟添加展开类，触发动画
    setTimeout(() => {
      dropdownState.value.expanded = true;
    }, 10);
  } else {
    // 先移除展开类，再隐藏
    dropdownState.value.expanded = false;
  }
};

// 点击外部关闭下拉菜单
const handleClickOutside = (event) => {
  const dropdown = document.querySelector('.user-dropdown');
  const avatarBtn = document.querySelector('.avatar-btn');
  if (dropdown && !dropdown.contains(event.target) && avatarBtn && !avatarBtn.contains(event.target)) {
    dropdownState.value.expanded = false;
    // 延迟隐藏，让关闭动画完成
    setTimeout(() => {
      dropdownState.value.show = false;
    }, 300);
  }
};

// 退出登录函数
const handleLogout = () => {
  // 清除用户信息（如果有）
  // 可以添加清除localStorage或其他存储的用户信息的逻辑
  
  // 关闭下拉菜单
  dropdownState.value.expanded = false;
  setTimeout(() => {
    dropdownState.value.show = false;
  }, 300);
  
  // 跳转到登录页面
  router.push('/login');
};

// 添加点击外部事件监听
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

// 清理事件监听
onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});
</script>

<template>
  <nav class="guide-bar">
    <div class="guide-bar-container">
      <!-- 左侧logo或标题 -->
      <div class="guide-bar-logo">
        <h1>数字人体可视化</h1>
      </div>
      
      <!-- 右侧内容 -->
      <div class="guide-bar-right">
        <!-- 导航链接 -->
        <div class="guide-bar-nav">
          <a href="#" class="nav-link">首页</a>
          <a href="#" class="nav-link">器官列表</a>
          <a href="#" class="nav-link">3D模型</a>
          <a href="#" class="nav-link">关于我们</a>
        </div>
        
        <!-- 个人中心 -->
        <div class="user-profile">
          <!-- 头像按钮 -->
          <button 
            class="avatar-btn" 
            @click.stop="toggleDropdown"
            aria-haspopup="true"
            :aria-expanded="dropdownState.show"
          >
            <img 
              src="https://avatars.githubusercontent.com/u/186171457?v=4&size=64" 
              alt="用户头像" 
              class="avatar-img"
            >
          </button>
          
          <!-- 下拉菜单 -->
          <div 
            v-if="dropdownState.show" 
            class="user-dropdown"
            :class="{ expanded: dropdownState.expanded }"
            @click.stop
          >
            <!-- 用户信息 -->
            <div class="dropdown-header">
              <div class="user-info">
                <img 
                  src="https://avatars.githubusercontent.com/u/186171457?v=4&size=64" 
                  alt="用户头像" 
                  class="dropdown-avatar"
                >
                <div class="user-details">
                  <div class="user-name">3风2</div>
                  <div class="user-nickname">Norman</div>
                </div>
              </div>
            </div>
            
            <!-- 功能列表 -->
            <ul class="dropdown-menu">
              <li class="menu-item">
                <a href="#" class="menu-link">
                  <span class="menu-icon">👤</span>
                  <span class="menu-text">个人资料</span>
                </a>
              </li>
              <li class="menu-item">
                <a href="#" class="menu-link">
                  <span class="menu-icon">⚙️</span>
                  <span class="menu-text">设置</span>
                </a>
              </li>
              <li class="menu-item">
                <a href="#" class="menu-link">
                  <span class="menu-icon">📊</span>
                  <span class="menu-text">数据管理</span>
                </a>
              </li>
              <li class="menu-item">
                <a href="#" class="menu-link">
                  <span class="menu-icon">💬</span>
                  <span class="menu-text">反馈建议</span>
                </a>
              </li>
              <li class="menu-divider"></li>
              <li class="menu-item">
                <a href="#" class="menu-link logout" @click.prevent="handleLogout">
                  <span class="menu-icon">🚪</span>
                  <span class="menu-text">退出登录</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
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

/* 右侧内容容器 */
.guide-bar-right {
  display: flex;
  align-items: center;
  gap: 20px;
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

/* 个人中心样式 */
.user-profile {
  position: relative;
}

/* 头像按钮 */
.avatar-btn {
  background: none;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 50%;
  transition: background-color 0.2s ease;
  display: flex;
  align-items: center;
  justify-content: center;
}

.avatar-btn:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.avatar-img {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid transparent;
  transition: border-color 0.2s ease;
}

.avatar-btn:hover .avatar-img {
  border-color: #64ffda;
}

/* 下拉菜单 */
.user-dropdown {
  position: absolute;
  top: 100%;
  right: 0;
  margin-top: 8px;
  background-color: #16213e;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  width: 280px;
  z-index: 1001;
  overflow: hidden;
  border: 1px solid rgba(255, 255, 255, 0.1);
  opacity: 0;
  transform: translateX(20px) translateY(10px);
  max-height: 0;
  transition: all 0.3s ease;
  animation: fadeInRight 0.5s ease forwards;
}

/* 下拉菜单展开状态 */
.user-dropdown.expanded {
  opacity: 1;
  transform: translateX(0) translateY(0);
  max-height: 500px;
  box-shadow: 
    0 12px 40px rgba(0, 0, 0, 0.5),
    0 0 35px rgba(100, 255, 218, 0.4);
  background: rgba(22, 33, 62, 0.95);
  border-color: rgba(100, 255, 218, 0.3);
}

/* 新增动画 */
@keyframes fadeInRight {
  to {
    opacity: 1;
    transform: translateX(0) translateY(0);
    max-height: 500px;
  }
}

/* 下拉菜单头部 */
.dropdown-header {
  padding: 16px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.3s ease 0.1s;
}

.user-dropdown.expanded .dropdown-header {
  opacity: 1;
  transform: translateY(0);
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.dropdown-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  object-fit: cover;
  transform: scale(0.9);
  transition: all 0.3s ease 0.2s;
  opacity: 0;
}

.user-dropdown.expanded .dropdown-avatar {
  transform: scale(1);
  opacity: 1;
}

.user-details {
  display: flex;
  flex-direction: column;
  opacity: 0;
  transform: translateX(10px);
  transition: all 0.3s ease 0.3s;
}

.user-dropdown.expanded .user-details {
  opacity: 1;
  transform: translateX(0);
}

.user-name {
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  margin-bottom: 2px;
  background: linear-gradient(45deg,#ADEED9 33%, #56DFCF 66%, #0ABAB5 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.user-nickname {
  font-size: 14px;
  color: #a0aec0;
}

/* 下拉菜单项 */
.dropdown-menu {
  list-style: none;
  margin: 0;
  padding: 8px 0;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.3s ease 0.4s;
}

.user-dropdown.expanded .dropdown-menu {
  opacity: 1;
  transform: translateY(0);
}

.menu-item {
  margin: 0;
  padding: 0;
  opacity: 0;
  transform: translateX(10px);
  transition: all 0.3s ease;
}

/* 为每个菜单项设置不同的动画延迟 */
.menu-item:nth-child(1) {
  transition-delay: 0.5s;
}

.menu-item:nth-child(2) {
  transition-delay: 0.55s;
}

.menu-item:nth-child(3) {
  transition-delay: 0.6s;
}

.menu-item:nth-child(4) {
  transition-delay: 0.65s;
}

.menu-item:nth-child(5) {
  transition-delay: 0.7s;
}

.menu-item:nth-child(6) {
  transition-delay: 0.75s;
}

.user-dropdown.expanded .menu-item {
  opacity: 1;
  transform: translateX(0);
}

.menu-link {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px 16px;
  color: #e0e0e0;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.2s ease;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.menu-link::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 0;
  background-color: rgba(100, 255, 218, 0.1);
  transition: width 0.2s ease;
  z-index: 0;
}

.menu-link:hover::before {
  width: 100%;
}

.menu-link:hover {
  color: #64ffda;
}

.menu-icon {
  font-size: 16px;
  width: 16px;
  text-align: center;
  z-index: 1;
  transition: transform 0.2s ease;
}

.menu-link:hover .menu-icon {
  transform: scale(1.2);
}

.menu-text {
  flex: 1;
  z-index: 1;
}

/* 分隔线 */
.menu-divider {
  height: 1px;
  background-color: rgba(255, 255, 255, 0.1);
  margin: 8px 0;
  opacity: 0;
  transition: opacity 0.3s ease 0.7s;
}

.user-dropdown.expanded .menu-divider {
  opacity: 1;
}

/* 退出登录样式 */
.menu-link.logout::before {
  background-color: rgba(239, 68, 68, 0.1);
}

.menu-link.logout:hover {
  color: #ef4444;
}
</style>