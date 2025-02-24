<template>
    <div class="register-container">
      <h2>Register</h2>
      <form @submit.prevent="register">
        <div>
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="form.username" required />
        </div>
  
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="form.email" required />
        </div>
  
        <div>
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="form.password" required />
        </div>
  
        <button type="submit">Register</button>
        <p v-if="message">{{ message }}</p>
      </form>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue';
  import axios from 'axios';
  
  const form = ref({
    username: '',
    email: '',
    password: '',
  });
  
  const message = ref<string | null>(null);
  
  const register = async () => {
    try {
      const response = await axios.post('http://localhost:8080/auth/register', form.value);
      message.value = response.data.message; // Show success message from backend
    } catch (error) {
      message.value = 'Registration failed! Please try again.';
      console.error('Registration error:', error);
    }
  };
  </script>