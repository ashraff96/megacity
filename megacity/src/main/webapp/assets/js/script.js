const nav = document.getElementById('nav'),
      menuBtn = document.getElementById('menu'),
      closeBtn = document.getElementById('close'),
      header = document.getElementById('header'),
      navLinks = nav.querySelectorAll('.nav-link'),
      profile = document.querySelector('.header-container .profile'),
      user = document.getElementById('user');

const hideMenu = () => {
    nav.classList.remove('open-menu');
    document.body.style.overflow = 'auto';
}

navLinks.forEach((navLink) => {
    navLink.addEventListener('click', () => {
        hideMenu();
    });
})

menuBtn.onclick = () => {
    nav.classList.add('open-menu');
    document.body.style.overflow = 'hidden';
    profile.classList.remove('active');
}

closeBtn.onclick = () => { 
    hideMenu();
}

user.onclick = () => {
   profile.classList.toggle('active')
}

window.onscroll = () => {
   profile.classList.remove('active');
}

window.onscroll = () => {
    const screenWidth = window.innerWidth;
    const scrollHeight = screenWidth < 480 ? 35 : 100;

    if (window.scrollY >= scrollHeight) {
        header.classList.add('nav-dark-bg');
    } else {
        header.classList.remove('nav-dark-bg');
    }
};