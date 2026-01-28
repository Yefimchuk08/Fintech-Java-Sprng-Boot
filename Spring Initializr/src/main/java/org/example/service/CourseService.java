package org.example.service;

import org.example.model.Course;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    void saveCourse(Course course);
    Course getCourseById(long id);
    void deleteCourseById(long id);
    Page<Course> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
