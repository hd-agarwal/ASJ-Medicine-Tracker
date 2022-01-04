package com.examples.medicinetracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserInformationViewModel(application: Application):AndroidViewModel(application)
{
 val allNames: LiveData<List<UserInformation>>
 val allMedicines: LiveData<List<MedicineInformation>>
 init {
 val daouser=AppInformationDatabase.getDatabase(application).UserInformationDao()
  val daomeds=AppInformationDatabase.getDatabase(application).MedicineInformationDao()
  val repository=DatabaseRepository(daouser,daomeds)
  allNames=repository.allNames
  allMedicines=repository.getallMedicines
 }

}