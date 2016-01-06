(function(){
  angular.module('NoteWrangler')
    .config(function($routeProvider){
      $routeProvider.when('/notes', {
        templateUrl: 'templates/pages/notes/notes-index.html'
      })
      .when('/users', {
        templateUrl: 'templates/pages/users/users-index.html'
      })
      .when('/', {
        templateUrl: 'templates/pages/notes/notes-index.html'
      })
      .otherwise( {redirectTo: '/'});
    });

})();
