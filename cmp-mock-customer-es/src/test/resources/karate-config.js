function() {
    karate.configure('report', { showLog: true, showAllSteps: false } );
    return esUrl='http://localhost:8070/es/customer';
}