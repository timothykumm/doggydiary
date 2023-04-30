import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'       
import HelloWorld from '../components/HelloWorld.vue'    

const routes = [
  { path: '/', component: HomeView },
  { path: '/hello', component: HelloWorld },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router