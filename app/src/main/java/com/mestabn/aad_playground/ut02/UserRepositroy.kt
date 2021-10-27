package com.mestabn.aad_playground.ut02

/**
 * Ejercicio para ver y practicar el patrón repositorio con dos fuentes de datos:
 *  - Memoria
 *  - SharedPreferences
 */
class UserRepository(
    private val memDataSource: MemDataSource<UserModel>,
    private val sharPrefDataSource: SharPrefDataSource<UserModel>,
) {

    /**
     * Guardo siempre en disco, en fichero shared preferences
     */
    fun saveUsers(users: List<UserModel>) {
        if (users.isNotEmpty()) {
            sharPrefDataSource.save(users)
        }
    }

    fun fetchUser(id: Int): UserModel? {
        //Busco en memoria
        var user = memDataSource.fetch(id)
        if (user == null) {
            //al no obtener resultados, busco en disco
            user = sharPrefDataSource.fetch(id)
            user?.run {
                memDataSource.save(mutableListOf(this))
            }
        }
        return user
    }

    /**
     * Primero se busca en memoria, si hay datos se devuelven. Sino, se recupera de disco, se
     * guarda en memoria y se devuelven los datos.
     */
    fun fetchAllUsers(): List<UserModel>? {
        //Obtengo de memoria, si hay datos los devuelvo sino, continuo el código.
        var users = memDataSource.fetchAll()
        if (users == null){
            //Obtengo de disco, si hay datos, los guardo en memoria y devuelvo los datos, sino, continuo
            users = sharPrefDataSource.fetchAll()
            users?.run {
                memDataSource.save(this)
            }
        }
        return users
    }
}
