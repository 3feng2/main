<template>
  <div 
    class="organ-item"
    :class="{ 
      active: selectedOrgan?.id === organ.id,
      expanded: organ.expanded 
    }"
    @click="handleOrganClick"
  >
    <div class="organ-content">
      <div class="organ-header-row">
        <span class="organ-title">{{ organ.name }}</span>
        <span class="organ-dot"></span>
        <span class="organ-subtitle">Organ</span>
      </div>
      
      <!-- 折叠内容 -->
      <div class="organ-details">
        <p class="organ-description">{{ organ.description }}</p>
        <OrganStats :stats="organ.stats" />
      </div>
    </div>
    <div class="organ-roll"></div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import OrganStats from './OrganStats.vue';

const props = defineProps({
  organ: {
    type: Object,
    required: true
  },
  selectedOrgan: {
    type: Object,
    default: null
  }
});

const emit = defineEmits(['click']);

const handleOrganClick = () => {
  emit('click', props.organ);
};
</script>

<style scoped>
/* 器官项样式 */
.organ-item {
  /* Glassmorphism Core */
  background: rgba(255, 255, 255, 0.15); /* Light frosted glass */
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  
  padding: 1.5rem 2rem;
  border-radius: 25px;
  transition: all 0.3s ease;
  opacity: 0;
  transform: translateX(150px);
  animation: fadeInRight 0.8s ease forwards;
  cursor: pointer;
  
  /* Soft Border */
  border: 1px solid rgba(255, 255, 255, 0.2);
  
  /* Glow */
  box-shadow: 
    0 8px 32px rgba(0, 0, 0, 0.4),
    0 0 25px rgba(0, 191, 255, 0.4);
  
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  position: relative;
  overflow: hidden;
  min-height: 60px;
  width: 200px;
  max-width: calc(100% - 120px);
}

.organ-item:hover {
  transform: translateX(-20px) translateY(-5px);
  box-shadow: 
    0 12px 40px rgba(0, 0, 0, 0.5),
    0 0 35px rgba(0, 191, 255, 0.6);
  background: rgba(255, 255, 255, 0.25);
  border-color: rgba(255, 255, 255, 0.3);
}

.organ-item.active {
  transform: translateX(-20px) translateY(-5px);
  background: rgba(255, 255, 255, 0.2);
  box-shadow: 
    0 12px 40px rgba(0, 0, 0, 0.5),
    0 0 35px rgba(0, 191, 255, 0.6);
  border-color: rgba(255, 255, 255, 0.3);
}

.organ-item.expanded {
  background: rgba(255, 255, 255, 0.25);
  width: 65%;
  transform: translateX(-20px) translateY(-5px);
  box-shadow: 
    0 16px 48px rgba(0, 0, 0, 0.55),
    0 0 40px rgba(0, 191, 255, 0.7);
  border-color: rgba(255, 255, 255, 0.3);
}

.organ-item.expanded:hover {
  background: rgba(255, 255, 255, 0.3);
  box-shadow: 
    0 18px 52px rgba(0, 0, 0, 0.6),
    0 0 45px rgba(0, 191, 255, 0.8);
  border-color: rgba(255, 255, 255, 0.4);
}

/* 新增动画 */
@keyframes fadeInRight {
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

/* 器官内容 */
.organ-content {
  flex: 1;
  z-index: 2;
  display: flex;
  flex-direction: column;
}

/* 器官标题行 */
.organ-header-row {
  display: flex;
  align-items: center;
  gap: 0;
  flex-wrap: nowrap;
  min-height: 24px;
}

.organ-title {
  font-size: 1.25rem;
  font-weight: 600;
  background: linear-gradient(45deg,#ADEED9 33%, #56DFCF 66%, #0ABAB5 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  transition: all 0.3s ease;
  flex-shrink: 0;
}

.organ-dot {
  width: 4px;
  height: 4px;
  background-color: #888;
  border-radius: 50%;
  margin: 0 12px;
  flex-shrink: 0;
}

.organ-subtitle {
  font-size: 0.9rem;
  color: #888;
  font-weight: 400;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  flex-shrink: 0;
}

/* 折叠详情内容 */
.organ-details {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  max-height: 0;
  opacity: 0;
  overflow: hidden;
  transition: all 0.5s ease;
}

.organ-item.expanded .organ-details {
  max-height: 500px;
  opacity: 1;
  padding-top: 1rem;
}

/* 详情内容内部元素动画 */
.organ-description {
  color: #ccc;
  line-height: 1.6;
  font-size: 0.95rem;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease 0.1s;
}

.organ-item.expanded .organ-description {
  opacity: 1;
  transform: translateY(0);
}

/* 渐变滚动效果 */
.organ-roll {
  width: 40px;
  height: 100%;
  background: linear-gradient(90deg, rgb(242, 159, 255) 0%, rgb(124, 153, 255) 100%);
  border-radius: 0 8px 8px 0;
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;
  flex-shrink: 0;
}

.organ-item:hover .organ-roll {
  width: 50px;
  transform: scaleX(1.1);
}

.organ-roll::after {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
  animation: rollShine 3s infinite;
}

@keyframes rollShine {
  0% { left: -100%; }
  100% { left: 100%; }
}

/* 响应式设计 */
@media (max-width: 1024px) {
  .organ-item {
    width: 100%;
    transform: translateX(0);
  }

  @keyframes fadeInRight {
    to {
      opacity: 1;
      transform: translateX(0);
    }
  }
}

@media (max-width: 768px) {
  /* 器官项响应式 */
  .organ-item {
    padding: 1.5rem;
  }

  .organ-header-row {
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  .organ-dot {
    margin: 0 8px;
  }

  .organ-details {
    gap: 0.5rem;
  }
}
</style>