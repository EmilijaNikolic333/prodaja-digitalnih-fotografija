/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author nikol
 */
public interface Repository<T> {
    List<T> getAll(T parm, String uslov) throws Exception;
    void add(T parm) throws Exception;
    void edit(T parm) throws Exception;
    void delete(T parm) throws Exception;
    List<T> getAll();
    T getObject(T parm, String uslov) throws Exception;
    
}
