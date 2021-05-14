function() {
    karate.configure('report', { showLog: true, showAllSteps: false } );
    return esUrl='http://cmp-poc-api.com:8070/es/customer';
}