import { createRouter, createWebHistory } from 'vue-router';
import Register from '../views/Register.vue'; // Ensure this import is correct

const routes = [
  { path: '/register', component: Register },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
