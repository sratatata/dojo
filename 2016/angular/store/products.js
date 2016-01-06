(function(){

  var app = angular.module('store-products', []);

  app.directive('productTitle', function(){
    return {
      restrict: 'E',
      templateUrl: 'product-title.html'
    };
  });

  app.directive('productDescription', function(){
    return {
      restrict: 'E',
      templateUrl: 'product-description.html'
    };
  });

  app.directive('productSpecification', function(){
    return {
      restrict: 'E',
      templateUrl: 'product-specification.html'
    };
  });

  app.directive('productReviews', function(){
    return {
      restrict: 'E',
      templateUrl: 'product-reviews.html',
      controller: function(){
          this.review = {};

          this.addReview = function(product){
            product.reviews.push(this.review);
            this.review = {};
          };
        },
      controllerAs: 'reviewCtrl',
    };

  });

  app.directive('productPanels', function(){
    return {
      restrict: 'E',
      templateUrl: 'product-panels.html',
      controller: function(){
        this.tab = 1
        this.selectTab = function(setTab) {
          this.tab = setTab;
        };
        this.isSelected = function(checkTab){
          return this.tab === checkTab;
        };
      },
      controllerAs: 'panel'
    };
  });
})();
