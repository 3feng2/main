<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import OrganItem from './OrganItem.vue';

// 定义组件属性
const props = defineProps({
  organs: {
    type: Array,
    required: true
  },
  selectedOrgan: {
    type: Object,
    default: null
  }
});

// 定义事件
const emit = defineEmits(['organClick']);

// 获取DOM引用
const rightSection = ref(null);

// 处理器官点击事件
const handleOrganClick = (organ) => {
  emit('organClick', organ);
};


</script>

<template>
  <div class="right-section" ref="rightSection">
    <div class="organs-list">
      <OrganItem
        v-for="organ in organs"
        :key="organ.id"
        :organ="organ"
        :selectedOrgan="selectedOrgan"
        @click="handleOrganClick"
      />
    </div>
  </div>
</template>

<style scoped>
/* 右侧滑动区域 */
.right-section {
  width: 50%;
  margin-left: 50%;
  padding: 5rem 0;
  overflow-y: auto;
  max-height: 100vh;
  position: relative;
}

/* 自定义滚动条 */
.right-section::-webkit-scrollbar {
  width: 8px;
}

.right-section::-webkit-scrollbar-track {
  background: rgba(26, 26, 26, 0.5);
}

.right-section::-webkit-scrollbar-thumb {
  background: rgba(10, 186, 181, 0.5);
  border-radius: 4px;
}

.right-section::-webkit-scrollbar-thumb:hover {
  background: rgba(10, 186, 181, 0.7);
}

/* 器官列表容器 */
.organs-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  align-items: flex-end;
  padding-right: 180px;
  position: relative;
}

/* 参考元素 */
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

/* 响应式设计 */
@media (max-width: 1024px) {
  .right-section {
    width: 100%;
    margin-left: 0;
    padding: 3rem 1.5rem;
  }

  .organs-list {
    align-items: center;
    padding-right: 0;
  }
}
</style>