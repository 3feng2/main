import { createRouter, createWebHistory } from 'vue-router'
import AppRoot from '../components/AppRoot.vue'
import LoginPage from '../components/LoginPage.vue'
import SingUp from '../components/SingUp.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginPage
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage
  },
  {
    path:'/signup',
    name:'SignUp',
    component:SingUp
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
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router