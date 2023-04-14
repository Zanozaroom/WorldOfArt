package com.example.navigation
/**
 * Класс для описания потока навигации по внешним графам
 * Сколько вложенных графов (кроме графа Активити) будет находиться в mainGraph модуля навигатора,
 * столько здесь должно быть вложенных классов
 */
sealed class NavigationFlow
object NavigationBottomMenuFlow : NavigationFlow()
object NavigationLoginFlow : NavigationFlow()
