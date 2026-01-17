<template>
  <div class="organ-stats">
    <div class="stat" v-for="(value, key) in stats" :key="key">
      <span class="stat-value">{{ value }}</span>
      <span class="stat-label">{{ key }}</span>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  stats: {
    type: Object,
    required: true
  }
});
</script>

<style scoped>
/* 器官统计 */
.organ-stats {
  display: flex;
  gap: 1.5rem;
  flex-wrap: wrap;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease 0.3s;
}

/* 当父组件展开时，显示统计信息 */
:host-context(.organ-item.expanded) .organ-stats {
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

/* 当父组件展开时，显示每个统计项 */
:host-context(.organ-item.expanded) .stat {
  opacity: 1;
  transform: translateY(0);
}

/* 为每个统计项添加延迟动画 */
:host-context(.organ-item.expanded) .stat:nth-child(1) { transition-delay: 0.4s; }
:host-context(.organ-item.expanded) .stat:nth-child(2) { transition-delay: 0.5s; }
:host-context(.organ-item.expanded) .stat:nth-child(3) { transition-delay: 0.6s; }

.stat-value {
  font-size: 1.1rem;
  font-weight: 700;
  color: #0ABAB5;
}

.stat-label {
  font-size: 0.75rem;
  color: #888;
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* 响应式设计 */
@media (max-width: 768px) {
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