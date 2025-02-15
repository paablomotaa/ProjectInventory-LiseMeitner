package app.features.inventorycreation.ui.edition

sealed class InventoryEditionStateView {
        data object Loading: InventoryEditionStateView()
        data object Success: InventoryEditionStateView()
}