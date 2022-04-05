<template>
  <b-navbar-brand href="#">
       <!-- Logo icon -->
    <router-link tag="a" to="/">
        <b class="logo-icon">
            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
            <!-- Light Logo icon -->
            <img src="../../../assets/images/site-logo.png" alt="homepage" >
            
        </b>
        <!-- End Logo icon -->
        <!-- Logo text -->
        <!-- <span class="logo-text"> -->
            <!-- Light Logo text -->
           <!--  <img src="../../../assets/images/logo-text.png"  alt="homepage">
        </span> -->
        </router-link>
  </b-navbar-brand>
</template>

<script>
export default {
    name: 'LogoDark',
}
</script>
