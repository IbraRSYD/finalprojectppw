open class Employee(private var name: String, private var age: Int, private var salary: Double) {
    fun displayInfo() {
        println("Name: $name, Age: $age, Salary: $salary")
    }

    fun getSalary(): Double {
        return salary
    }

    fun setSalary(newSalary: Double) {
        if (newSalary > 0) {
            salary = newSalary
        } else {
            println("Salary must be positive")
        }
    }
}

class Manager(name: String, age: Int, salary: Double, private var bonus: Double) : Employee(name, age, salary) {
    fun displayManagerInfo() {
        displayInfo()
        println("Bonus: $bonus")
    }

    fun getBonus(): Double {
        return bonus
    }

    fun setBonus(newBonus: Double) {
        if (newBonus >= 0) {
            bonus = newBonus
        } else {
            println("Bonus must be non-negative")
        }
    }
}
class Company {
    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun displayAllEmployees() {
        for (employee in employees) {
            if (employee is Manager) {
                employee.displayManagerInfo()
            } else {
                employee.displayInfo()
            }
            println()
        }
    }

    fun getTotalSalary(): Double {
        var totalSalary = 0.0
        for (employee in employees) {
            totalSalary += employee.getSalary()
        }
        return totalSalary
    }
}
fun main() {
    val company = Company()

    while (true) {
        println("Pilih opsi:")
        println("1. Tambah Karyawan")
        println("2. Tambah Manajer")
        println("3. Tampilkan Semua Karyawan")
        println("4. Tampilkan Total Gaji")
        println("5. Keluar")
        print("Pilihan Anda: ")

        val choice = readLine()?.toIntOrNull()
        when (choice) {
            1 -> addEmployee(company)
            2 -> addManager(company)
            3 -> displayAllEmployees(company)
            4 -> displayTotalSalary(company)
            5 -> {
                println("Keluar dari program.")
                return
            }
            else -> {
                println("Pilihan tidak valid, silakan coba lagi.")
            }
        }
        println()
    }
}

fun addEmployee(company: Company) {
    println("Masukkan nama karyawan:")
    val name = readLine().orEmpty()
    println("Masukkan usia karyawan:")
    val age = readLine()?.toIntOrNull() ?: return println("Usia tidak valid")
    println("Masukkan gaji karyawan:")
    val salary = readLine()?.toDoubleOrNull() ?: return println("Gaji tidak valid")
    val employee = Employee(name, age, salary)
    company.addEmployee(employee)
    println("Karyawan berhasil ditambahkan.")
}

fun addManager(company: Company) {
    println("Masukkan nama manajer:")
    val name = readLine().orEmpty()
    println("Masukkan usia manajer:")
    val age = readLine()?.toIntOrNull() ?: return println("Usia tidak valid")
    println("Masukkan gaji manajer:")
    val salary = readLine()?.toDoubleOrNull() ?: return println("Gaji tidak valid")
    println("Masukkan bonus manajer:")
    val bonus = readLine()?.toDoubleOrNull() ?: return println("Bonus tidak valid")
    val manager = Manager(name, age, salary, bonus)
    company.addEmployee(manager)
    println("Manajer berhasil ditambahkan.")
}

fun displayAllEmployees(company: Company) {
    println("Daftar Semua Karyawan:")
    company.displayAllEmployees()
}

fun displayTotalSalary(company: Company) {
    println("Total Gaji Semua Karyawan: ${company.getTotalSalary()}")
}
