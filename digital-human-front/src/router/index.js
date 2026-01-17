import { createRouter, createWebHistory } from 'vue-router'
import WelcomePage from '../components/WelcomePage.vue'
import AppRoot from '../components/AppRoot.vue'

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: WelcomePage
  },
  {
    path: '/app',
    name: 'App',
    component: AppRoot
  },
  {
    path: '/modulemanager',
    name: 'ModuleManager',
    component: AppRoot // 暂时使用AppRoot作为占位，后续可以替换为实际的模块管理组件
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router