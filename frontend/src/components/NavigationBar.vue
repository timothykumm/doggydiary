<template>
  <div name="complete-navbar">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <img src="../assets/logo.png" width="50" height="50" class="d-inline-block align-top" alt="">
    <a class="navbar-brand" href="#"> {{ "‍ &#8203;" + name }} </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</div>
</template>

<script>
export default {
  name: 'NavigationBar',
  props: {
    name: String
  }
}
</script>

<style>
div[name="complete-navbar"] {
  /* Damit navbar oben kleben bleibt */
  position: fixed;
  top: 0;
  width: 100%;
}
</style>