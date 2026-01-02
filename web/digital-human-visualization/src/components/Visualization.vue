<template>
  <div class="visualization">
    <div class="human-silhouette"></div>
  </div>
</template>

<script setup>
</script>

<style scoped>
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
  background: linear-gradient(180deg,#ADEED9 33%, #56DFCF 66%, #0ABAB5 100%);
  /* 移除星星造型，使用简单的div形状 */
  border-radius: 50% 50% 50% 50% / 60% 60% 40% 40%;
  transition: all 0.3s ease;
}

.visualization:hover .human-silhouette {
  transform: scale(1.1);
  filter: brightness(1.3);
}

/* 动画 */
@keyframes fadeInScale {
  to {
    opacity: 1;
    transform: scale(1);
  }
}

/* 响应式设计 */
@media (max-width: 768px) {
  .visualization {
    width: 250px;
    height: 250px;
  }

  .human-silhouette {
    width: 120px;
    height: 160px;
  }
}
</style>