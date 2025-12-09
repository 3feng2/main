<script setup>
import { ref, reactive } from 'vue';

// Organ data
const organs = reactive([
  {
    id: 1,
    name: 'Brain',
    description: 'The central nervous system that controls all human functions, containing approximately 100 billion neurons, responsible for thinking, memory, emotions, and motor control.',
    stats: {
      weight: '1.4kg',
      neurons: '100B',
      power: '20W'
    },
    expanded: false
  },
  {
    id: 2,
    name: 'Heart',
    description: 'The body\'s blood-pumping organ, beating approximately 72 times per minute, pumping about 7500 liters of blood daily, providing oxygen and nutrients to the entire body.',
    stats: {
      heartRate: '72',
      bloodVolume: '7500L',
      weight: '300g'
    },
    expanded: false
  },
  {
    id: 3,
    name: 'Lungs',
    description: 'The main organs of the respiratory system, responsible for gas exchange between oxygen and carbon dioxide. The surface area of adult lungs is approximately 70 square meters, equivalent to a tennis court.',
    stats: {
      surfaceArea: '70㎡',
      respiratoryRate: '12-20',
      vitalCapacity: '5-6L'
    },
    expanded: false
  },
  {
    id: 4,
    name: 'Liver',
    description: 'The largest internal organ in the human body, with multiple functions including metabolism, detoxification, and protein synthesis, processing approximately 1.5 liters of blood per minute.',
    stats: {
      weight: '1.5kg',
      functions: '500+',
      bloodFlow: '1.5L'
    },
    expanded: false
  },
  {
    id: 5,
    name: 'Kidneys',
    description: 'Responsible for filtering blood, excreting waste, and regulating fluid balance, filtering approximately 180 liters of blood daily and producing about 1.5 liters of urine.',
    stats: {
      filtration: '180L',
      urine: '1.5L',
      weight: '150g'
    },
    expanded: false
  },
  {
    id: 6,
    name: 'Digestive System',
    description: 'Consisting of the mouth, esophagus, stomach, small intestine, and large intestine, responsible for food digestion and nutrient absorption, with a total length of approximately 9 meters.',
    stats: {
      length: '9m',
      cycle: '24-72h',
      bacteria: '100T'
    },
    expanded: false
  }
]);

// 选中的器官
const selectedOrgan = ref(null);

// 处理器官点击
const handleOrganClick = (organ) => {
  // 切换展开状态
  organ.expanded = !organ.expanded;
  // 设置选中状态
  selectedOrgan.value = organ;
};
</script>

<template>
  <div class="container">
    <!-- 左侧固定区域 -->
    <div class="left-section">
      <div class="logo">DIGITAL HUMAN</div>
      <h1 class="title">Digital Human Visualization System</h1>
      <p class="subtitle">Explore the digital representation of human internal organs</p>
      <div class="visualization">
        <div class="human-silhouette"></div>
      </div>
    </div>

    <!-- 右侧滑动区域 -->
    <div class="right-section">
      <div class="organs-list">
        <!-- 参考元素 -->
        <div class="reference-div"></div>
        
        <div 
          v-for="organ in organs" 
          :key="organ.id"
          class="organ-item"
          :class="{ 
            active: selectedOrgan?.id === organ.id,
            expanded: organ.expanded 
          }"
          @click="handleOrganClick(organ)"
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
              <div class="organ-stats">
                <div class="stat" v-for="(value, key) in organ.stats" :key="key">
                  <span class="stat-value">{{ value }}</span>
                  <span class="stat-label">{{ key }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="organ-roll"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  min-height: 100vh;
}

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
  background: linear-gradient(45deg, #667eea 0%, #764ba2 100%);
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

.visualization {
  width: 300px;
  height: 300px;
  border: 2px solid #333;
  border-radius: 50%;
  background: radial-gradient(circle, #2a2a2a 0%, #0a0a0a 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
  opacity: 0;
  transform: scale(0.8);
  animation: fadeInScale 1s ease forwards 0.6s;
}

.human-silhouette {
  width: 150px;
  height: 200px;
  background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
  clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%, 32% 57%, 2% 35%, 39% 35%);
  transition: all 0.3s ease;
}

.visualization:hover .human-silhouette {
  transform: scale(1.1);
  filter: brightness(1.3);
}

/* 右侧滑动区域 */
.right-section {
  width: 50%;
  margin-left: 50%;
  padding: 5rem 0;
  overflow: visible;
}

.organs-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  align-items: flex-end;
  padding-right: 180px;
  position: relative;
}

/* 添加参考元素 */
.reference-div {
  position: absolute;
  right: 40px; 
  top: 0;
  width: 20px;
  height: 20px;
  background: transparent;
  pointer-events: none;
  z-index: 5;
}

/* 器官项样式 */
.organ-item {
  background: transparent;
  padding: 1.5rem 2rem;
  border-radius: 8px;
  transition: all 0.3s ease;
  opacity: 0;
  transform: translateX(150px);
  animation: fadeInRight 0.8s ease forwards;
  cursor: pointer;
  border: 2px solid transparent;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  position: relative;
  overflow: hidden;
  min-height: 60px;
  width: 200px;
  max-width: calc(100% - 120px);
}

.organ-item:nth-child(1) { animation-delay: 0.2s; }
.organ-item:nth-child(2) { animation-delay: 0.4s; }
.organ-item:nth-child(3) { animation-delay: 0.6s; }
.organ-item:nth-child(4) { animation-delay: 0.8s; }
.organ-item:nth-child(5) { animation-delay: 1s; }
.organ-item:nth-child(6) { animation-delay: 1.2s; }

.organ-item:hover {
  transform: translateX(-20px);
  background: #2a2a2a;
  box-shadow: 0 5px 20px rgba(102, 126, 234, 0.2);
  border-color: rgba(102, 126, 234, 0.3);
}

.organ-item.active {
  transform: translateX(-20px);
  background: #2a2a2a;
  border-color: #667eea;
  box-shadow: 0 5px 20px rgba(102, 126, 234, 0.3);
}

.organ-item.expanded {
  background: #2a2a2a;
  width: 65%;
  transform: translateX(-20px);
}

.organ-item.expanded:hover {
  background: #2d2d2d;
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
  background: linear-gradient(45deg, #667eea 0%, #764ba2 100%);
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

/* 器官统计 */
.organ-stats {
  display: flex;
  gap: 1.5rem;
  flex-wrap: wrap;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease 0.3s;
}

.organ-item.expanded .organ-stats {
  opacity: 1;
  transform: translateY(0);
}

.stat {
  display: flex;
  flex-direction: column;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.3s ease;
}

.organ-item.expanded .stat {
  opacity: 1;
  transform: translateY(0);
}

.organ-item.expanded .stat:nth-child(1) { transition-delay: 0.4s; }
.organ-item.expanded .stat:nth-child(2) { transition-delay: 0.5s; }
.organ-item.expanded .stat:nth-child(3) { transition-delay: 0.6s; }

.stat-value {
  font-size: 1.1rem;
  font-weight: 700;
  color: #667eea;
}

.stat-label {
  font-size: 0.75rem;
  color: #888;
  text-transform: uppercase;
  letter-spacing: 1px;
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

/* 动画 */
@keyframes fadeInUp {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInScale {
  to {
    opacity: 1;
    transform: scale(1);
  }
}

@keyframes fadeInLeft {
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

/* 响应式设计 */
@media (max-width: 1024px) {
  .container {
    flex-direction: column;
  }

  .left-section {
    width: 100%;
    height: 50vh;
    position: relative;
  }

  .right-section {
    width: 100%;
    margin-left: 0;
    padding: 3rem 1.5rem;
  }

  .organs-list {
    align-items: center;
    padding-right: 0;
  }

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
  .logo {
    font-size: 2rem;
  }

  .title {
    font-size: 2rem;
  }

  .subtitle {
    font-size: 1rem;
  }

  .visualization {
    width: 250px;
    height: 250px;
  }

  .human-silhouette {
    width: 120px;
    height: 160px;
  }

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

  .organ-stats {
    gap: 1rem;
  }

  .stat-value {
    font-size: 1rem;
  }

  .stat-label {
    font-size: 0.7rem;
  }
}
</style>
