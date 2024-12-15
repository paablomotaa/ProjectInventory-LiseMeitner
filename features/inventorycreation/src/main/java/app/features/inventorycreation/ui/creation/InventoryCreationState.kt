package app.features.inventorycreation.ui.creation

import java.time.LocalDate
import java.util.Date

data class InventoryCreationState (

    //Variables
    val code:String = "",
    val name:String = "",
    val shortName:String = "",
    val description:String = "",
    val type:String = "",
    val dateActive: Date = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
    val dateProgress: Date = dateActive,
    val dateHistory: Date = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),


    //Estados
    val isCodeError:Boolean = false,
    val isNameError:Boolean = false,
    val isDescriptionError:Boolean = false,
    val isErrorCreation : Boolean = false,
    val isShortNameError:Boolean = false,
    val Success:Boolean = false,
    val isLoading:Boolean = false,
    val NoData:Boolean = false,
    val expanded:Boolean = false,


    //Formatos
    val ErrorCodeFormat:String = "",
    val ErrorNameFormat:String = "",
    val ErrorShortNameFormat:String = "",
    val ErrorDescriptionFormat:String = "",
    val isEmpty: String = "",

)