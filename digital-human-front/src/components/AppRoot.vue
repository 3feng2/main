<script setup>
import { ref, reactive } from 'vue';
import GuideBar from './GuideBar.vue';
import LeftSection from './LeftSection.vue';
import RightSection from './RightSection.vue';

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
  <div class="app-root">
    <!-- 路由出口 -->
    <router-view />
    
    <!-- 导航栏和应用内容（非欢迎页面） -->
    <template v-if="$route.path !== '/'">
      <!-- 导航栏 -->
      <GuideBar />
      
      <div class="container">
        <!-- 左侧固定区域 -->
        <LeftSection />

        <!-- 右侧滑动区域 -->
        <RightSection 
          :organs="organs" 
          :selectedOrgan="selectedOrgan" 
          @organClick="handleOrganClick"
        />
      </div>
    </template>
  </div>
</template>

<style scoped>
.app-root {
  width: 100%;
  min-height: 100vh;
  overflow: hidden;
}

.container {
  display: flex;
  min-height: 100vh;
  position: relative;
  overflow: hidden;
  padding-top: 60px; /* 为导航栏留出空间 */
}
</style>