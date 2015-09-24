var gulp = require('gulp');

/* PLUG-INS */

var clean = require('gulp-clean');
var mocha = require('gulp-mocha');
var jslint = require('gulp-jslint');


gulp.task('default', ['clean', 'test', 'build'], function () {
});

/* CLEAN */

gulp.task('clean', function () {
    gulp.src(['./target', 'node_modules', 'public/bower_components'], {read: false})
        .pipe(clean());
});

/* TEST */

gulp.task('test', ['jshint'], function(){
    gulp.src('test/**/*.js')
        .pipe(mocha({
            reporter: 'spec'
        }))
        .on("error", function (err) {
            console.log(err.toString());
            this.emit('end');
        });
});

gulp.task('jshint', function () {
	gulp.src(['./server.js', './lib/*.js'])
		.pipe(jslint({
            nomen: true,
            white: true,
            errorsOnly: false
        }))
        .on("error", function (err) {
            console.log(err.toString());
            this.emit('end');
        });
});

/* BUILD */

gulp.task('build', function () {

});