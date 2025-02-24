import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Import router

import './styles/global.css'; // Import global styles

const app = createApp(App);
app.use(router); // Register router
app.mount('#app');
