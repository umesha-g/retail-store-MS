/*
 * This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/.
 */

// @author Umesha Madushan

package com.umeshag.retailstorerm;

public interface LoginListener {// interface allows to have empty methods
    // empty method for switch with selection method later
    void onLoginSuccess(String id,String job,String username);
}